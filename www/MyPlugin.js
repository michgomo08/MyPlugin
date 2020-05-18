module.exports = {
    testMichael: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "testMichael", [message]);
    },
    new_activity: function(message, success, error) {
        cordova.exec(success, error, "MyPlugin", "new_activity", []);
    }
};