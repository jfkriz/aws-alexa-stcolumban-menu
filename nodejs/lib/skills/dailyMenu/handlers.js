'use strict';

var services = require('../../services');

var handlers = {
    'GetMenuIntent': function() {
        var _this = this;
        _this.emit(':tell', 'This is under construction.');
    }
};

module.exports = handlers;