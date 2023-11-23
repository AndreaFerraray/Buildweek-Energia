import { Card, Col, Container, Row } from "react-bootstrap";
import NavBar from "./Navbar";
import Footer from "./Footer";
import { useSelector } from "react-redux";

const UserPage = () => {
  const user = useSelector(state => state.login.user);
  return (
    <div className="UserPage">
      <NavBar />
      <Row
        className="d-flex flex-row"
        style={{
          height: "70vh"
        }}>
        <Col>
          <Container className="mt-5 d-flex justify-content-center">
            <Row>
              <Col>
                {user ? (
                  <>
                    <Card style={{ width: "18rem" }}>
                      <Card.Img
                        variant="top"
                        src={user.avatar}
                      />
                      <Card.Body>
                        <Card.Title>{user.username}</Card.Title>
                        <Card.Text>
                          {user.firstName}
                          {user.lastName}
                        </Card.Text>
                      </Card.Body>
                    </Card>
                  </>
                ) : (
                  <p>User not log</p>
                )}
              </Col>
            </Row>
          </Container>
        </Col>
      </Row>
    </div>
  );
};
export default UserPage;
