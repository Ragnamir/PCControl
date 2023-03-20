var ws;
var socket;
var sessionId = "";
var launchersPreviously = 0;

function updateCell(check, everChecked, fired, programmed, row, i, j, k) {
    let cell;

    if ($('#cell_'+ i + '_' + j +'_' + k).get(0)) {
        cell = $('#cell_'+ i + '_' + j +'_' + k).get(0);
    } else {
        cell = document.createElement("td");
        cell.id = 'cell_'+ i + '_' + j +'_' + k;
        row.append(cell);
    }

    cell.innerHTML = "";

    if (check) {
        cell.innerHTML += "c";
    }
    if (everChecked) {
        cell.innerHTML += "l";
    }
    if (fired) {
        cell.innerHTML += "f";
    }
    if (programmed) {
        cell.innerHTML += "p";
    }
}

function updateRow(board, table, i, j) {
    let row;
    if ($('#row_' + i + '_' + j).get(0)) {
        row = $('#row_' + i + '_' + j).get(0);
    } else {
        row = document.createElement("tr");
        row.id = 'row_'+ i + '_' + j;

        table.append(row);
    }

    for (let k = 0; k < board.check.length; k++) {
        let check = board.check[k];
        let everChecked = board.everChecked[k];
        let fired = board.fired[k];
        let programmed = board.programmed[k];
        updateCell(check, everChecked, fired, programmed, row, i, j, k)
    }
}

function updateTable(boards, table, i) {
    for (let j = 0; j < boards.length; j++) {
        let board = boards[j];
        updateRow(board, table, i, j)
    }
}

function updateLauncher(launcher, container, i) {
    let launcherDiv;
    let launcherName;
    let launcherState;
    let launcherTable;

    if ($('#div_'+i).get(0)) {
        launcherDiv = $('#div_'+i).get(0);
        launcherName = $('#name_'+i).get(0);
        launcherState = $('#state_'+i).get(0);
        launcherTable = $('#table_'+i).get(0);
    } else {
        launcherDiv = document.createElement("div");
        launcherDiv.id = 'div_' + i;
        launcherDiv.className = "launcher_div";
        container.append(launcherDiv);

        launcherName = document.createElement("p");
        launcherDiv.id = 'name_' + i;
        launcherName.className = "name_p";
        launcherDiv.append(launcherName)

        launcherState = document.createElement("p");
        launcherDiv.id = 'state_' + i;
        launcherState.innerHTML = launcher.launcherState;
        launcherState.className = "state_p";
        launcherDiv.append(launcherState)

        launcherTable = document.createElement("table");
        launcherTable.id = 'table_' + i;
        launcherTable.className = "table";
        launcherDiv.append(launcherTable)
    }

    launcherName.innerHTML = launcher.name;
    launcherState.innerHTML = launcher.launcherState;

    updateTable(launcher.boards, launcherTable, i);
}

function removeLauncher(i) {

}

function updateAll(dto) {
    for (let i = 0; i < dto.launchersInUse.length; i++) {
        let launcher = dto.launchersInUse[i];
        updateLauncher(launcher, $("#content").get(0), i);
    }

    if (dto.launchersInUse.length < launchersPreviously) {
        for (let i = dto.launchersInUse.length; i < launchersPreviously; i++) {
            removeLauncher(i);
        }
    }

    launchersPreviously = dto.launchersInUse.length;
}

function requestUpdate() {
    ws.send("/app/getdata", {}, "");
}

function connect() {
    socket = new SockJS('/pccontrol/websocket');
    ws = Stomp.over(socket);

    ws.connect({}, function(frame) {
        let url = ws.ws._transport.url;
        url = url.replace(
            "ws://localhost:8080/pccontrol/websocket/",  "");
        url = url.replace("/websocket", "");
        url = url.replace(/^[0-9]+\//, "");
        console.log("Your current session is: " + url);
        sessionId = url;

        ws.subscribe('/topic/data', function(message) {
            console.log(message);
            dto = JSON.parse(message.body);
            updateAll(dto);
        });
        requestUpdate();
    }, function(error) {
        alert("STOMP error " + error);
    });
}

window.onload = function () {
    connect();
}