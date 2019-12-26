var stompClient = null;
var gamestate = null;
var gid = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/tictactoe-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/tictactoe/moves', function (move) { //was /topic/greetings //move was greeting
            showGreeting(JSON.parse(move.body).content);
        });
        1
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendSlotNumber() {
    stompClient.send("/app/hello", {}, JSON.stringify(
        {'content': $("#slotNumber").val()}
    ));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function sendMove(x, y, z) {
    stompClient.send("/app/hello", {}, JSON.stringify({'content': z, 'row': x, 'col': y}));
}

function senCreds() {
    stompClient.send("/app/jello", {}, JSON.stringify(
        {'name': $("#usernameSlot").val(), 'password': $("#pswSlot").val()}
    ));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#sendo").click(function () {
        sendSlotNumber();
    });
    $("#sendCredentials").click(function () {
        senCreds();
        $("#login-content").hide();
        $("#main-content").show();
    })

    $("#board").on("click", "td", function () {
        sendMove($(this).attr('x'), $(this).attr('y'), $(this).attr('z'));
    });
});