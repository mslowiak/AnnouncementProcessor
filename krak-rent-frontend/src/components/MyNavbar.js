import React, { Component } from 'react';
import {Navbar, Nav, Button, ButtonToolbar} from 'react-bootstrap'
import {Link} from 'react-router-dom'

class MyNavbar extends Component {
  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Nav className="mr-auto">
          <Link to="/" style={{ textDecoration: 'none', color: 'white' }}>LOGO</Link>          
        </Nav>
        <ButtonToolbar>
          <Button className="mx-1" variant="success">Moje konto</Button>
          <Button className="mx-1" variant="outline-success">Dodaj ogłoszenie</Button>
        </ButtonToolbar>
      </Navbar>
    );
  }
}

export default MyNavbar;