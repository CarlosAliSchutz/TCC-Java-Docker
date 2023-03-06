import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useMeusPosts } from "../../../api/post/meusPosts";
import { usePost } from "../../../api/post/post";
import { useLogout } from "../../../api/user/logout.api";
import { useProfile } from "../../../api/user/me";
import { USER_KEY } from "../../../constants";
import useGlobalUser from "../../../context/user/user.context";
import { Button } from "../../components";
import { Input } from "../../components/input/input.component";
import "./index.css";

export function PerfilScreen() {
  const { getProfile, perfil } = useProfile();
  const { postCriarMeuPost, post } = usePost();
  const { postLogout } = useLogout();
  const { getPosts, posts } = useMeusPosts();
  const [formInput, setFormInput] = useState({
    conteudo: "",
    disponivel: "PRIVADO",
  });
  const [user, setUser] = useGlobalUser();

  useEffect(() => {
    getProfile();
    getPosts();
  }, []);

  function handlePost(event) {
    event.preventDefault();
    postCriarMeuPost({
      conteudo: formInput.conteudo,
      disponivel: "PRIVADO",
    });
  }

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
  }

  const nomeUsuario = perfil.apelido ? perfil.apelido : perfil.nome;

  useEffect(() => {
    setTimeout(() => {
      if (perfil?.idUsuario && perfil?.nome == undefined) {
        console.log(perfil?.idUsuario);
        localStorage.removeItem(USER_KEY);
      }
    }, 1200);
  });

console.log(perfil?.idUsuario)

  function handleClickLogout() {
    postLogout();

    setUser(null);
    localStorage.removeItem(USER_KEY);
  }

  const dataNascimneto = new Date(perfil.dataNascimento);
  const dataFormatada = dataNascimneto.toLocaleDateString("pt-BR", {
    timeZone: "UTC",
  });

  return (
    <>
      <div className="profile">
        <div className="profileCover">
          <h1 className="Logo">connect music</h1>
          <Button onClick={handleClickLogout} className="logout">LOGOUT</Button>

          <img
            className="profileUserImg"
            src={perfil?.imgPerfil}
            alt="foto de perfil"
          />
        </div>
        <div className="profileInfo">
          <h4 className="profileInfoName">{nomeUsuario}</h4>
        </div>
        <div className="infoNavegacao">
          <Link to={"/home"}> Pagina Inicial</Link>
          <Link to={"/perfil/amizades"}> Pedidos de amizade</Link>
        </div>


        <div className="infoUsuario">
          <h1>Perfil</h1>
          <h2>Nome Completo: {perfil?.nome}</h2>
          <h3>Apelido: {perfil?.apelido}</h3>
          <h3>Email: {perfil?.email}</h3>
          <h3>Data de Nascimento: {dataFormatada}</h3>
        </div>
        <div className="profilePublicacoes">
          <h1>Publique com seus amigos o que está escutando hoje</h1>
          <div className="publicacoes">
            <img
              className="imgProfile"
              src={perfil?.imgPerfil}
              alt="foto de perfil"
            />

            <Input
              type="text"
              className="profileButton"
              onChange={handleChange}
              name="conteudo"
              placeholder="Compartilhe aqui a URL da música"
            />
            <Button onClick={handlePost}>Postar</Button>
          </div>
        </div>
        <div>
          <h1 className="publiText">Publicações</h1>
          {posts &&
            posts.map((post, index) => {
              return (
                <div className="post" key={index}>
                  <div className="postPerfil">
                    <img
                      className="imgProfile"
                      src={post?.autorFoto}
                      alt="Foto do perfil"
                    />
                    <h1>
                      {post.autorApelido ? post.autorApelido : post.autorNome}
                    </h1>
                  </div>

                  <iframe
                    width="500"
                    height="318"
                    src={post?.conteudo}
                    title="YouTube video player"
                  ></iframe>
                  <p>👍 {post?.curtidas ? post?.curtidas : 0}</p>
                </div>
              );
            })}
        </div>
      </div>
    </>
  );
}
