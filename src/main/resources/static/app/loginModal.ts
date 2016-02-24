import {Component} from 'angular2/core';
import { Router, RouterLink } from 'angular2/router';
import { Http, Headers } from 'angular2/http';
import { contentHeaders } from './headers';


@Component({
    selector: 'login-modal',
    templateUrl: 'templates/loginModal.html',
    styleUrls: ['css/loginModal.css']
})


export class LoginModal {

    constructor(public router: Router, public http: Http) {
    }

    login(event, username, password) {
        event.preventDefault();
        var body = 'username='+username+'&password='+password; //JSON.stringify({ 'username': username, 'password':password });
        this.http.post('http://localhost:8080/auth', body, { headers: contentHeaders})
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

}

