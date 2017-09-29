'use strict';

var services = require('../../services');

var handlers = {
    'GetDailyBibleVerseIntent': function() {
        console.log('GetDailyBibleVerseIntent starting');
        var bibleVerseUrl = process.env.DailyBibleVerseUrl;
        console.log(`DailyBibleVerseUrl: ${bibleVerseUrl}`);
        var _this = this;
        try {
            services.HttpClient.get(bibleVerseUrl).headers({'Accept': 'application/json'}).send().then((response) => {
                console.log(`GetDailyBibleVerseIntent success: ${response}`);
                var r = JSON.parse(response.body).votd;
                var book = r.reference.substring(0, r.reference.lastIndexOf(' '));
                var chapterAndVerse = r.reference.substring(r.reference.lastIndexOf(' ')).split(':', 2);
                var chapter = chapterAndVerse[0];
                var verse = chapterAndVerse[1];
    
                _this.emit(':tell', `Today's bible verse is from ${book}, chapter ${chapter}, verse ${verse}.  ${r.content}`);
            }, (error) => {
                console.log(`GetDailyBibleVerseIntent error: ${error}`);
                _this.emit(':tell', 'Sorry, there was a problem reading today\'s bible verse');
            }).catch((err) => {
                console.log(`GetDailyBibleVerseIntent http catch: ${err}`);
            });
        } catch(e) {
            console.log(`GetDailyBibleVerseIntent exception: ${e}`);
            _this.emit(':tell', 'Sorry, there was a problem reading today\'s bible verse');
        }
    }
};

module.exports = handlers;