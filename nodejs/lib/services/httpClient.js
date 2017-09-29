'use strict';

var https = require('https');
var http = require('http');
var url = require('url');

const HTTPS_PROTOCOL = 'https:';

class HttpRequest {
    constructor(uri, method) {
        var obj = url.parse(uri);
        this.protocol = obj.protocol;
        this.path = obj.path;
        this.port = obj.port || (obj.protocol === HTTPS_PROTOCOL ? 443 : 80);
        this.host = obj.host;
        this.method = method;
        this._headers = {};
    }

    headers(obj) {
        this._headers = obj;
        return this;
    }

    body(obj) {
        this.body = obj;
        return this;
    }

    send() {
        return new Promise((resolve, reject) => {
            var options = {
                protocol : this.protocol,
                host : this.host,
                port : this.port,
                path : this.path,
                method : this.method,
                headers : this._headers
            };

            var protocol = this.protocol === HTTPS_PROTOCOL ? https : http;
            var req = protocol.request(options, (response) => {
                var chunks = [];
                response.on('data', (chunk) => {
                    chunks.push(chunk);
                });

                response.on('end', () => {
                    var responseStr = chunks.join('');
                    var responseObj = {
                        statusCode : response.statusCode,
                        statusText : response.statusText,
                        body : responseStr,
                        headers : response.headers
                    };

                    resolve(responseObj);
                });
            });
            
            req.on('error', (err) => {
                reject(err);
            });

            req.end();
        });
    }
}

class HttpClient {
    /**
     * @static
     * @param {string} url
     * @returns {HttpRequest}
     * @memberof HttpClient
     */
    static post(url) {
        return new HttpRequest(url, 'POST');
    }

    /**
     * @static
     * @param {string} url
     * @returns {HttpRequest}
     * @memberof HttpClient
     */
    static get(url) {
        return new HttpRequest(url, 'GET');
    }
}

module.exports = HttpClient;