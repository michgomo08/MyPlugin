module.exports = {
    testMichael: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "testMichael", [message]);
    },
    buscarLector: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "buscarLector", [message]);
    },
    buscarLector2: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "buscarLector2", [message]);
    }
};