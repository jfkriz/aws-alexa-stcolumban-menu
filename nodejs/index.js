var Alexa = require('alexa-sdk');
var lib = require('./lib');
var appId = process.env.AlexaSkillId;

exports.handler = function(event, context, callback) {
    console.log('Starting it up');
    var alexa = Alexa.handler(event, context, callback);
    alexa.appId = appId;
    alexa.registerHandlers(lib.Skills.DailyMenu.Handlers, lib.Skills.BibleVerse.Handlers);
    console.log('Executing handlers');
    alexa.execute();
};