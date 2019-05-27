import React, { Component } from 'react';
import Axios from 'axios';
import { Form , Button, Row, Col } from 'react-bootstrap';
import ErrorLabel from '../components/ErrorLabel';

class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = { 
        validated: false,
        isError: false,
        errorMessage: "",
        username: "",
        password: ""
    };
  }
  
  handleChange(event, componentName) {
    this.setState({[componentName]: event.target.value})
  }

  handleSubmit(event) {
    const form = event.currentTarget;
    event.preventDefault();
    event.stopPropagation();
    if (form.checkValidity() === true) {
        let config = {headers: {'Content-Type': 'application/json'}};
        let body = this.stringifyLoginRequestBody();
        Axios.post("/auth/login", body, config)
        .then(results => {
            var ls = require('local-storage');
            ls.set('isUserLogged', true)
            ls.set('authToken', results.data)
            this.props.history.push('/')
        })
        .catch((error) => {
            if (error.response) {
                this.setState({
                    isError: true,
                    errorMessage: error.response.data
                })
            }
        })
    }
    this.setState({ validated: true });
  }

  stringifyLoginRequestBody() {
    return JSON.stringify({ 
        username: this.state.username,
        password: this.state.password
      }
    )
  }

  render() {
    const { validated } = this.state;
    return (
      <div className="auth-container">
                <div className="wrapper">
                    <ErrorLabel 
                        isVisible={this.state.isError} 
                        errorMessage={this.state.errorMessage}
                    />
                    <Form 
                        noValidate 
                        validated={validated}
                        onSubmit={e => this.handleSubmit(e)}
                    >
                        <Form.Group as={Row} controlId="formGroupUsername">
                            <Form.Label column sm="5">Nazwa użytkownika</Form.Label>
                            <Col sm="7">
                                <Form.Control 
                                    required
                                    type="text" 
                                    placeholder="Nazwa użytkownika" 
                                    onChange={event => this.handleChange(event, "username")}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Nie podano nazwy użytkownika 
                                </Form.Control.Feedback>
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row} controlId="formGroupPassword">
                            <Form.Label column sm="5">Hasło</Form.Label>
                            <Col sm="7">
                                <Form.Control 
                                    required
                                    type="password" 
                                    placeholder="Hasło" 
                                    onChange={event => this.handleChange(event, "password")}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Nie podano hasła
                                </Form.Control.Feedback>
                            </Col>
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Zaloguj
                        </Button>
                    </Form>
                </div>
            </div>
    );
  }
}

export default LoginPage;