import { Button, Modal } from "antd";
import { useNavigate } from "react-router-dom";

const UnenrollButton = ({ courseId }) => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const handleUnenroll = () => {
    const options = {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    fetch(`http://localhost:8080/enrollment/${courseId}`, options)
      .then(() => {
        navigate("/dashboard");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleConfirm = () => {
    Modal.confirm({
      title: "Are you sure you want to unenroll?",
      onOk: handleUnenroll,
      onCancel: () => {},
    });
  };

  return (
    <Button
      type="default"
      style={{ color: "red", border: "1px solid red" }}
      onClick={handleConfirm}
    >
      Unenroll
    </Button>
  );
};

export default UnenrollButton;
