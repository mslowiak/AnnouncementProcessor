import React, { Component } from "react";
import "../css/unauthorized-page.css";

class UnauthorizedPage extends Component {
  constructor(props) {
    super(props);
    this.handleLogin = this.handleLogin.bind(this);
  }

  handleLogin() {
    this.props.history.push("/login");
  }

  render() {
    return (
      <div align="center" className="unauth-container">
        Brak uprawnień
        <div>
          <img
            className="img-unauth"
            src="https://cdn4.iconfinder.com/data/icons/web-pages-seo/512/5-512.png"
          />
        </div>
        <div className="loggin-button" onClick={() => this.handleLogin()}>
          Zaloguj się, by móc wykonać daną akcję!
        </div>
      </div>
    );
  }
}

export default UnauthorizedPage;
