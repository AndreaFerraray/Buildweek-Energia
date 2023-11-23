import { Button, Col, Container, Form, InputGroup, Row } from "react-bootstrap";
const SideBar = () => {
  const hadlerSubmit = e => {
    e.preventDefault();
  };
  return (
    <>
      <div className="SideBar">
        <Container>
          <Row
            className="d-flex flex-row"
            style={{
              height: "70vh"
            }}>
            <Col className="border">
              <Row className="mt-3 d-flex flex-column g-4">
                <Col>Elemento 1</Col>
                <Col>Elemento 2</Col>
                <Col>Elemento 3</Col>
                <Col>Elemento 4</Col>
                <Col>Elemento 5</Col>
              </Row>
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
};
export default SideBar;
