const webpush = require('web-push');

main();

function main() {
  webpush.setVapidDetails(
      'mailto:john.gorter@gmail.com',
      'BDjjueETb-hrVLOY0OAx5k4vQVP9HZxSyS4a7Jm7pY0p3WAtdcyy5ILQkV5AOSdjHhsEKnO5sbzQPpxwXoVWzqM',
      'OaKtWx_BConq1IjrT2Ug3X1amwJ2tNRKDVIuE5tWplY'
  );

  const pushSubscription = {
    "endpoint": "https://fcm.googleapis.com/fcm/send/ceD0jLQ1A08:APA91bErdqD_GmsAh3bm2RowkIJ7HBECPf-87aPJ3N631rL81tgxTM6yh4lISqtS5deI911AH8536efY2v_axTc7LHSZMyXNVnBuB2Lp3jZ4-kXPcOoexlOjM9lN7djS0WsqGW4Rh_sx",
    "expirationTime": null,
    "keys": {
      "p256dh": "BAKPCuaPBCQWqlpkwHw1gHte4b4U808UUjG7i0eKvCdepLnCV4fKX1yO7LF-ZuvKXujvQQCvtlZWop2s5zjGXro=",
      "auth": "oGjTmNSsmeGL5LXb837nxQ=="
    }
  };

  setInterval(() => {
    console.log('pushing a notification');
    webpush.sendNotification(pushSubscription, 'Your Push Payload Text');
  }, 2000, 3);
}
