module.exports = {

    echo: function(phrase, cb) {
        cordova.exec(cb, null, "MyPlugin", "echo", [phrase]);
    },

    alert: function(title, message, buttonLabel, successCallback) {

        cordova.exec(successCallback,

            null, // No failure callback

            "MyPlugin",

            "alert",

            [title, message, buttonLabel]);

    }

};