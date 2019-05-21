import React, { Component } from 'react';
import { Form , Button } from 'react-bootstrap';
import Axios from 'axios';
import ErrorLabel from '../components/ErrorLabel';

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
                    console.log(error.response.data);
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
            <div>
                <ErrorLabel 
                    isVisible={this.state.isError} 
                    errorMessage={this.state.errorMessage}
                />
                <Form 
                    noValidate 
                    validated={validated}
                    onSubmit={e => this.handleSubmit(e)}
                >
                    <Form.Group controlId="formGroupName">
                        <Form.Label>Nazwa wyświetlana</Form.Label>
                        <Form.Control 
                            required
                            type="text" 
                            placeholder="Name" 
                            onChange={event => this.handleChange(event, "name")}
                        />
                        <Form.Control.Feedback type="invalid">
                            Nie podano nazwy
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group controlId="formGroupUsername">
                        <Form.Label>Nazwa użytkownika</Form.Label>
                        <Form.Control 
                            required
                            type="text" 
                            placeholder="Username" 
                            onChange={event => this.handleChange(event, "username")}
                        />
                        <Form.Control.Feedback type="invalid">
                            Nie podano nazwy użytkownika 
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group controlId="formGroupPassword">
                        <Form.Label>Hasło</Form.Label>
                        <Form.Control 
                            required
                            type="password" 
                            placeholder="Password" 
                            onChange={event => this.handleChange(event, "password")}
                        />
                        <Form.Control.Feedback type="invalid">
                            Nie podano hasła
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group controlId="formGroupEmail">
                        <Form.Label>Adres email</Form.Label>
                        <Form.Control 
                            required
                            type="email" 
                            placeholder="Enter email" 
                            onChange={event => this.handleChange(event, "email")}
                        />
                        <Form.Control.Feedback type="invalid">
                            Email jest niepoprawny lub nie podano go
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Rejestruj konto
                    </Button>
                </Form>
            </div>
        );
    }
}

export default RegisterPage;