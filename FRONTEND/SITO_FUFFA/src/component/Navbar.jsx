import { Button, Col, Form, Image, InputGroup } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import placeholderUser from "../asset/user_placeholder.png";
import { useSelector } from "react-redux";

const NavBar = () => {
  const user = useSelector(state => state.login.user);
  return (
    <div className="NavBar">
      <Navbar
        bg="dark"
        data-bs-theme="dark"
        expand="lg"
        className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="#home">LOGO</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="/">Home</Nav.Link>
              <Nav.Link href="/address">Address</Nav.Link>
            </Nav>

            <Nav>
              {user ? (
                <>
                  <Image
                    src={user.avatar}
                    alt="image profile"
                    roundedCircle
                    style={{ width: "60px", height: "60px", marginInline: "1rem" }}
                  />
                  <NavDropdown id="basic-nav-dropdown">
                    <Nav.Link href="/profile">Profile</Nav.Link>
                    <Nav.Link href="#action/3.2">Setting</Nav.Link>
                    <Nav.Link href="/address">Address</Nav.Link>
                    <NavDropdown.Divider />
                    <Nav.Link href="/logout">Logout</Nav.Link>
                  </NavDropdown>
                </>
              ) : (
                <>
                  <Nav.Link href="/register">Sign in</Nav.Link>
                  <Nav.Link href="/login">Login</Nav.Link>
                </>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

export default NavBar;
