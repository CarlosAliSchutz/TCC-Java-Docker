import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useLogin } from "../../../api/user/login.api";
import { Button, Input } from "../../components";
import { ToastContainer, toast } from 'react-toastify';
import useGlobalUser from "../../../context/user/user.context";

import "./index.css";

export function LoginScreen() {
  const [formInput, setFormInput] = useState({ email: "", password: "" });
  // const [error, setError] = useState();
  const {postLogin} = useLogin();
  const [user, setUser] = useGlobalUser();


  const navigate = useNavigate();

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
  }



  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const user = await postLogin({
        username: formInput.email,
        password: formInput.password,
      });

      setUser(user);
    } catch (error) {
      toast("E-mail ou senha invalidos")
    }
  }


  useEffect(() => {
    if (user) {
      navigate("/perfil");
    }
  }, [user, navigate]);


  function handleCadastro() {
    navigate("/cadastro")
  }


  return (
    <div className="login">
      <h1>Login</h1>

      <form className="login__form" onSubmit={handleSubmit}>
        <Input
          label="E-mail:"
          name="email"
          value={formInput.email}
          onChange={handleChange}
        />

        <Input
          label="Senha:"
          name="password"
          value={formInput.password}
          onChange={handleChange}
          type="password"
        />
        <ToastContainer />
        <Button type="submit">Entrar</Button>
      </form>
      <div>
        <Button onClick={handleCadastro} >Cadastro</Button>
      </div>
    </div>
  );
}
