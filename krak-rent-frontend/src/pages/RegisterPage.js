import React, { Component } from 'react';
import { Form , Button, Row, Col } from 'react-bootstrap';
import Axios from 'axios';
import ErrorLabel from '../components/ErrorLabel';
import '../css/register-page.css'

class RegisterPage extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            validated: false,
            isError: false,
            errorMessage: "",
            username: "",
            password: "",
            name: "",
            email: "",
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
            let body = this.stringifyRegisterRequestBody();
            Axios.post("/auth/register", body, config)
            .then(results => {
                this.props.history.push('/login')
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

    stringifyRegisterRequestBody() {
        return JSON.stringify({ 
            username: this.state.username,
            password: this.state.password,
            name: this.state.name,
            email: this.state.email,
          }
        )
    }

    render() {
        const { validated } = this.state;
        return (
            <div className="register-container">
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
                        <Form.Group as={Row} controlId="formGroupName">
                            <Form.Label column sm="5">Nazwa wyświetlana</Form.Label>
                            <Col sm="5">
                                <Form.Control 
                                    required
                                    type="text" 
                                    placeholder="Nazwa" 
                                    onChange={event => this.handleChange(event, "name")}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Nie podano nazwy
                                </Form.Control.Feedback>
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row} controlId="formGroupUsername">
                            <Form.Label column sm="5">Nazwa użytkownika</Form.Label>
                            <Col sm="5">
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
                            <Col sm="5">
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
                        <Form.Group as={Row} controlId="formGroupEmail">
                            <Form.Label column sm="5">Adres email</Form.Label>
                            <Col sm="5">
                                <Form.Control 
                                    required
                                    type="email" 
                                    placeholder="Email" 
                                    onChange={event => this.handleChange(event, "email")}
                                />
                                <Form.Control.Feedback type="invalid">
                                    Email jest niepoprawny lub nie podano go
                                </Form.Control.Feedback>
                            </Col>
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Rejestruj konto
                        </Button>
                    </Form>
                </div>
            </div>
        );
    }
}

export default RegisterPage;