System.register(['angular2/platform/browser', 'angular2/common', 'angular2/router', 'angular2/http', './app.component'], function(exports_1) {
    var browser_1, common_1, router_1, http_1, app_component_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (app_component_1_1) {
                app_component_1 = app_component_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(app_component_1.AppComponent, [
                common_1.FORM_PROVIDERS,
                router_1.ROUTER_PROVIDERS,
                http_1.HTTP_PROVIDERS /*,
                provide(AuthHttp, {
                    useFactory: (http) => {
                        return new AuthHttp(new AuthConfig({
                            tokenName: 'jwt'
                        }), http);
                    },
                    deps: [Http]
                })*/
            ]);
        }
    }
});
//# sourceMappingURL=main.js.map