'use strict';

var services = require('../../services');

const HTTPS_PROTOCOL = 'https:';

var handlers = {
    'GetDailyBibleVerseIntent': function() {
        var bibleVerseUrl = process.env.DailyBibleVerseUrl;
        var _this = this;
        try {
            services.HttpClient.get(bibleVerseUrl).headers({'Accept': 'application/json'}).send().then((response) => {
                var r = JSON.parse(response.body).votd;
                var book = r.reference.substring(0, r.reference.lastIndexOf(' '));
                var chapterAndVerse = r.reference.substring(r.reference.lastIndexOf(' ') + 1).split(':', 2);
                var chapter = chapterAndVerse[0];
                var verse = chapterAndVerse[1];
    
                _this.emit(':tell', `Today's bible verse is from ${book}, chapter ${chapter}, verse ${verse}.  ${r.content}`);
            }, (error) => {
                console.log(`GetDailyBibleVerseIntent error: ${error}`);
                _this.emit(':tell', 'Sorry, there was a problem reading today\'s bible verse');
            }).catch('error', (err) => {
                console.log(`GetDailyBibleVerseIntent catch: ${err}`);
                _this.emit(':tell', 'Sorry, there was a problem reading today\'s bible verse');
            });
        } catch(e) {
            console.log(`GetDailyBibleVerseIntent exception: ${e}`);
            _this.emit(':tell', 'Sorry, there was a problem reading today\'s bible verse');
        }
    }
};

module.exports = handlers;