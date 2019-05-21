export class AuthService {

  isAuth = false;

  signIn() {
    this.isAuth=true;
    return true;
  }

  signOut() {
    this.isAuth = false;
  }
}
