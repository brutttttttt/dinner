import { Component, View } from 'angular2/core';
import { Router, RouterLink } from 'angular2/router';
import { Http, Headers } from 'angular2/http';

@Component({
    selector: 'login',
})
@View({
    templateUrl: 'templates/login.html'
})
export class Login {
    constructor(public router: Router) {
    }

    login(event, username, password) {
        event.preventDefault();
        this.router.parent.navigateByUrl('/home');
    }

    signup(event) {
        event.preventDefault();
        this.router.parent.navigateByUrl('/signup');
    }
}