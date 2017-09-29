const services = require('../lib/services');
const http = require('http');

var main = function(response) {
    var content = '';
  
    response.on('data', function (chunk) {
      content += chunk;
    });
  
    response.on('end', function () {
      console.log(`Body: ${content}`);
    });
};

http.get('http://www.biblegateway.com/votd/get/?format=json&version=NIV', main).on('error', function(e) {
    console.log('Got error: ' + JSON.stringify(e));
});

services.HttpClient.get('http://www.biblegateway.com/votd/get/?format=json&version=NIV').send().then((response) => {
    console.log(`Response: ${response.body}`);    
});