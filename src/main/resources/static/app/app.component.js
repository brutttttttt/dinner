System.register(['angular2/core', 'angular2/router', './LoggedInOutlet', './home', './signup'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, LoggedInOutlet_1, home_1, signup_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (LoggedInOutlet_1_1) {
                LoggedInOutlet_1 = LoggedInOutlet_1_1;
            },
            function (home_1_1) {
                home_1 = home_1_1;
            },
            function (signup_1_1) {
                signup_1 = signup_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent(router) {
                    this.router = router;
                }
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app'
                    }),
                    core_1.View({
                        template: '<router-outlet></router-outlet>',
                        directives: [LoggedInOutlet_1.LoggedInRouterOutlet]
                    }),
                    router_1.RouteConfig([
                        { path: '/', redirectTo: ['/Home'] },
                        { path: '/home', component: home_1.Home, as: 'Home' },
                        // { path: '/login', component: Login, as: 'Login' },
                        { path: '/signup', component: signup_1.Signup, as: 'Signup' }
                    ]), 
                    __metadata('design:paramtypes', [router_1.Router])
                ], AppComponent);
                return AppComponent;
            })();
            exports_1("AppComponent", AppComponent);
        }
    }
});
//# sourceMappingURL=app.component.js.map