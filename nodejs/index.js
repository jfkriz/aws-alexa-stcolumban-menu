var Alexa = require('alexa-sdk');
var lib = require('./lib');
var appId = process.env.AlexaSkillId;

exports.handler = function(event, context, callback) {
    var alexa = Alexa.handler(event, context, callback);
    alexa.appId = appId;
    alexa.registerHandlers(lib.Skills.DailyMenu.Handlers, lib.Skills.BibleVerse.Handlers);
    alexa.execute();
};