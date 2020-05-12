var exec = require('cordova/exec');

exports.coolMethod = function(arg0, success, error) {
    exec(success, error, 'MyPlugin', 'coolMethod', [arg0]);
};



var PLUGIN_NAME = 'MyPlugin';

var MyPlugin = {
    echo: function(phrase, cb) {
        exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
    }
};

module.exports = MyPlugin;