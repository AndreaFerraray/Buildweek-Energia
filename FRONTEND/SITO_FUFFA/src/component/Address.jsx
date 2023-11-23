import { useEffect, useState } from "react";
import { Button, Col, Container, Form, FormSelect, Row } from "react-bootstrap";

const Address = () => {
  const [provinces, setProvinces] = useState();
  const [province, setProvince] = useState("");
  const [comuni, setComuni] = useState();
  const [comune, setComune] = useState("");
  //FORM
  const handlerSubmit = e => {
    e.preventDefault();
  };
  //CLICK PROVINCE
  const handleProvinceClick = async e => {
    //e.preventDefault();
    const risposta = await fetch("http://localhost:8080/auth/prov", {
      method: "GET"
    });
    if (risposta.ok) {
      const data = await risposta.json();
      console.log(data.content);
      setProvinces(data.content);
    }
  };
  //CHANGE PROV
  const handleProvinceChange = async event => {
    event.preventDefault();
    setProvince(event.target.value);
    const risposta = await fetch("http://localhost:8080/auth/comuni/" + event.target.value, {
      method: "GET"
    });
    if (risposta.ok) {
      const data = await risposta.json();
      console.log(data.content);
      setComuni(data.content);
    }
  };

  //CLIC COMUNI
  const handleComuniClick = async e => {
    e.preventDefault();
  };
  //CHANGE COMUNE
  const handleComuniChange = e => {
    e.preventDefault();
    setComune(e.target.value);
  };
  useEffect(() => {
    handleProvinceClick();
  }, []);

  return (
    <>
      <Button
        variant="success"
        href="/"
        className="m-5 registerButton">
        Home
      </Button>
      <Container
        className="d-flex align-items-center justify-content-center"
        style={{ height: "100vh" }}>
        <Form onSubmit={handlerSubmit}>
          <Row className="px-5">
            <Col>
              <Form.Group className="mb-3">
                <FormSelect
                  style={{ width: "600px" }}
                  name="province"
                  value={province}
                  onChange={handleProvinceChange}>
                  <option>SELEZIONA UNA PROVINCIA</option>
                  {provinces ? (
                    provinces.map((province, index) => (
                      <option
                        key={index}
                        value={province.abbreviation}>
                        {province.province}
                      </option>
                    ))
                  ) : (
                    <></>
                  )}
                </FormSelect>
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Group className="mb-3">
                  {province ? (
                    <FormSelect
                      name="comune"
                      value={comune}
                      onChange={handleComuniChange}
                      onClick={handleComuniClick}>
                      {comuni ? (
                        comuni.map((comuni, index) => (
                          <option
                            key={index}
                            value={comuni.city}>
                            {comuni.city}
                          </option>
                        ))
                      ) : (
                        <></>
                      )}
                    </FormSelect>
                  ) : (
                    <></>
                  )}
                </Form.Group>
              </Form.Group>
            </Col>
          </Row>
        </Form>
      </Container>
    </>
  );
};
export default Address;
