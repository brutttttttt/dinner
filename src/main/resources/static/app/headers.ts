import { Headers } from 'angular2/http';

export const contentHeaders = new Headers();

function getCookie(name) {
    let ca: Array<string> = document.cookie.split(';');
    let caLen: number = ca.length;
    let cookieName = name + "=";
    let c: string;

    for (let i: number = 0; i < caLen; i += 1) {
        c = ca[i].replace(/^\s\+/g, "");
        if (c.indexOf(cookieName) == 0) {
            return c.substring(cookieName.length, c.length);
        }
    }
    return "";
}
contentHeaders.append('Accept', 'application/json');
contentHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
//contentHeaders.append('X-AUTH-TOKEN', getCookie('XSRF-TOKEN'));
//contentHeaders.append('X-XSRF-TOKEN', getCookie('XSRF-TOKEN'));
