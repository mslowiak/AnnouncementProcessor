import React, { Component } from 'react';
import {Navbar, Nav, Button, ButtonToolbar} from 'react-bootstrap'
import { withRouter } from 'react-router-dom'
import {Link} from 'react-router-dom'

class MyNavbar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      announcements: []
    };
    this.handleLogout = this.handleLogout.bind(this)
    this.handleLogin = this.handleLogin.bind(this)
  }

  handleLogin() {
    this.props.history.push('/login')
  }

  handleLogout() {
    var ls = require('local-storage');
    ls.set('isUserLogged', false)
    ls.set('authToken', '')
    this.props.history.push('/')
  }

  render() {
    var ls = require('local-storage');
    var buttons = null
    if(ls.get('isUserLogged')){
      buttons = 
      <ButtonToolbar>
        <Button className="mx-1" variant="success">Moje konto</Button>
        <Button className="mx-1" variant="outline-success">Dodaj ogłoszenie</Button>
        <Button className="mx-1" variant="outline-success" onClick={this.handleLogout}>Wyloguj</Button>
      </ButtonToolbar>
    } else {
      buttons = 
      <ButtonToolbar>
        <Button className="mx-1" variant="success" onClick={this.handleLogin}>Zaloguj</Button>
      </ButtonToolbar>
    }
    return (
      <Navbar bg="dark" variant="dark">
        <Nav className="mr-auto">
          <Link to="/" style={{ textDecoration: 'none', color: 'white' }}>LOGO</Link>          
        </Nav>
        {buttons}
      </Navbar>
    );
  }
}

export default withRouter (MyNavbar);