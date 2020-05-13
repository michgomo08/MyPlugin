module.exports = {
    echo: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "testMichael", [message]);
    }
};