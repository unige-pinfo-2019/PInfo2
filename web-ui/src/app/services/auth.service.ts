export class AuthService {

  isAuth = true;//

  signIn() {
    this.isAuth=true;
    return true;
  }

  signOut() {
    this.isAuth = false;
  }
}
