import { Link } from "react-router-dom";
import { Col, Container, Row } from "react-bootstrap";
import NavBar from "./Navbar";
import Footer from "./Footer";
import SideBar from "./SideBar";

const Home = () => {
  return (
    <div className="Home">
      <NavBar />
      <Row
        className="d-flex flex-row"
        style={{
          height: "70vh"
        }}>
        <Col>
          <Container>
            <Row>
              <Col>{/* Contenuto della home */}</Col>
            </Row>
          </Container>
        </Col>
      </Row>
    </div>
  );
};
export default Home;
