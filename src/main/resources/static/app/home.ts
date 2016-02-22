import {Component,View} from 'angular2/core';
import { Http, Headers } from 'angular2/http';
import { Router, RouterLink } from 'angular2/router';

@Component({
    selector: 'my-app',
})

@View({
    templateUrl: 'templates/home.html'
})
export class Home {

    constructor(public http : Http, public router: Router){
    }

    logout(){

        this.http.get('http://localhost:8081/logout')
            .subscribe(
                response => {
                    //localStorage.removeItem('jwt');
                    //this.router.parent.navigateByUrl('/home');
                },
                error => {
                    alert(error.text());
                    console.log(error.text());
                }
            );

    }

}