import {View, Component} from 'angular2/core';
import {Location, RouteConfig, RouterLink, Router} from 'angular2/router';

import {LoggedInRouterOutlet} from './LoggedInOutlet';
import {Home} from './home';
import {Login} from './login';
import {Signup} from './signup';

@Component({
    selector: 'my-app'
})
@View({
    template: '<router-outlet></router-outlet>',
    directives: [ LoggedInRouterOutlet ]
})
@RouteConfig([
    { path: '/', redirectTo: ['/Home'] },
    { path: '/home', component: Home, as: 'Home' },
    { path: '/login', component: Login, as: 'Login' },
    { path: '/signup', component: Signup, as: 'Signup' }
])

export class AppComponent {
    constructor(public router: Router) {
    }
}