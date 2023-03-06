import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import { useCadastro } from "../../../api/user/cadastro";
import { Button, Input } from "../../components";

export function CadastroScreen() {
  const [formInput, setFormInput] = useState({
    nome: "",
    email: "",
    apelido: "",
    dataNascimento: "",
    senha: "",
    imgPerfil: "",
    permissoes: "ADMIN",
  });
  const { postCadastro, usuario } = useCadastro();
  const navigate = useNavigate();

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
  }

  function handleVoltar() {
    navigate("/");
  }

  function handleSubmit(event) {
    event.preventDefault();
    postCadastro({
      nome: formInput.nome,
      email: formInput.email,
      apelido: formInput.apelido,
      dataNascimento: formInput.dataNascimento,
      senha: formInput.senha,
      imgPerfil: formInput.imgPerfil,
      permissoes: ["ADMIN"],
    });
  }

  return (
    <>
      <div className="login">
        <h1>Cadastro</h1>

        <form className="login__form" onSubmit={handleSubmit}>
          <Input
            label="Nome:"
            name="nome"
            value={formInput.nome}
            type="text"
            onChange={handleChange}
          />

          <Input
            label="E-mail:"
            name="email"
            type="text"
            value={formInput.email}
            onChange={handleChange}
          />

          <Input
            label="Apelido:"
            name="apelido"
            type="text"
            value={formInput.apelido}
            onChange={handleChange}
          />

          <Input
            label="Data Nascimento:"
            name="dataNascimento"
            type="date"
            value={formInput.dataNascimento}
            onChange={handleChange}
          />

          <Input
            label="Senha:"
            name="senha"
            value={formInput.senha}
            onChange={handleChange}
            type="password"
          />

          <Input
            label="Foto Perfil"
            name="imgPerfil"
            value={formInput.imgPerfil}
            onChange={handleChange}
            type="text"
          />

          <select>
            <option value={formInput.permissoes}>ADMIN</option>
          </select>
          <ToastContainer />
          <Button type="submit">Cadastrar</Button>
        </form>
        <Button onClick={handleVoltar}>Voltar</Button>
      </div>
    </>
  );
}
