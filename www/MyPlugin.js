module.exports = {
    testMichael: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "testMichael", [message]);
    }
};