const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);

app.use(express.static('.'));

io.on('connection', function (socket) {
  socket.on('message', function (msg) {
    console.log("data received:", msg);
    io.emit('message', msg);
  });
});

http.listen(3000, function () {
  console.log('listening on *:3000');
});
