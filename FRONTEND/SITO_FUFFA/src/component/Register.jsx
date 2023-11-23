import { Button, Col, Container, Row } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import { useDispatch, useSelector } from "react-redux";
import { addUser } from "../redux/action";
import { useNavigate } from "react-router";

const Register = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handlerSubmit = async e => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    const risposta = await fetch("http://localhost:8080/auth/register", {
      method: "POST",
      headers: {
        "content-type": "application/json"
      },
      body: JSON.stringify({
        firstName: data.get("name"),
        lastName: data.get("surname"),
        username: data.get("username"),
        email: data.get("email"),
        password: data.get("password")
      })
    });
    if (risposta.ok) {
      const user = await risposta.json();
      dispatch(addUser(user));
      navigate("/profile");
    }
  };
  return (
    <div className="Register">
      <Button
        variant="success"
        href="/"
        className="m-5 registerButton">
        Home
      </Button>
      <Container className="mt-5 d-flex justify-content-center">
        <Row className="d-flex flex-column">
          <Col>
            <h2>Register Form</h2>
          </Col>
          <Col>
            <Form onSubmit={handlerSubmit}>
              <Row>
                <Col>
                  <Form.Group className="mb-3">
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                      type="text"
                      name="name"
                      id="name"
                      placeholder="Enter your name"
                    />
                  </Form.Group>
                  <Form.Group className="mb-3">
                    <Form.Label>Username</Form.Label>
                    <Form.Control
                      type="text"
                      name="username"
                      id="username"
                      placeholder="username..."
                    />
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Group className="mb-3">
                    <Form.Label>Surname</Form.Label>
                    <Form.Control
                      name="surname"
                      id="surname"
                      type="text"
                      placeholder="Enter your surname"
                    />
                  </Form.Group>
                </Col>
              </Row>
              <Form.Group>
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type="email"
                  name="email"
                  id="email"
                  placeholder="Enter email"
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  name="password"
                  id="password"
                  type="password"
                  placeholder="Password"
                />
              </Form.Group>
              <Button
                type="submit"
                className="registerButton">
                Submit
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Register;
