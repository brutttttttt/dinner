import { Component, View } from 'angular2/core';
import { Router, RouterLink } from 'angular2/router';
import { Http, Headers } from 'angular2/http';
import { contentHeaders } from './headers';

@Component({
    selector: 'login',
})
@View({
    templateUrl: 'templates/login.html'
})
export class Login {
    constructor(public router: Router, public http: Http) {
    }

    login(event, username, password) {
        event.preventDefault();
        var body = 'username='+username+'&password='+password; //JSON.stringify({ 'username': username, 'password':password });
        this.http.post('http://localhost:8081/auth', body, { headers: contentHeaders})
            .subscribe(
                response => {
                    localStorage.setItem('jwt', response.json().token);
                    this.router.parent.navigateByUrl('/home');
                },
                error => {
                    alert(error.text());
                    console.log(error.text());
                }
            );
    }

    create(event) {
        event.preventDefault();
        var body = 'username=test+'+Math.random()+'&password=dfg&email=email';//JSON.stringify({ 'username': 'test', 'password':'dfgdfgdf', email: 'email@gmail.com' });
        var head = new Headers();
        head.append('X-AUTH-TOKEN', localStorage.getItem('jwt'));
        head.append('Accept', 'application/json');
        head.append('Content-Type', 'application/x-www-form-urlencoded');

        this.http.post('http://localhost:8081/create', body, { headers: head})
            .subscribe(
                response => {
                    alert('create');
                },
                error => {
                    alert(error.text());
                    console.log(error.text());
                }
            );
    }

    signup(event) {
        event.preventDefault();
        this.router.parent.navigateByUrl('/signup');
    }
}