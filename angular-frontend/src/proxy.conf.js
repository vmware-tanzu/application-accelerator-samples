const PROXY_CONFIG = {
  "/api": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true,
    "logLevel": "debug",
    "onProxyReq": function(pr, req, res) {
  console.log(pr.getHeaders())
  console.log(req)
}
}
}

module.exports = PROXY_CONFIG;
