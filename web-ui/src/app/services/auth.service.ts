export class AuthService {

  isAuth = false;

  signIn() {
    this.isAuth=true;
    /*return new Promise(
      (resolve, reject) => {
        setTimeout(
          () => {
            this.isAuth = true;
            resolve(true);
          }, 2000
        );
        console.log("isAuth= "+ this.isAuth);
      }
    );*/
    return true;
  }

  signOut() {
    this.isAuth = false;
  }
}
