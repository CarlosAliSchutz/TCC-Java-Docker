import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useComentar } from "../../../api/comentario/comentar";
import { useTodosPosts } from "../../../api/post/todosPosts";
import { useLogout } from "../../../api/user/logout.api";
import { useProfile } from "../../../api/user/me";
import { USER_KEY } from "../../../constants";
import useGlobalUser from "../../../context/user/user.context";
import BotaoComentar from "../../../utils/img/botao-comentar.png";
import { Button, Input } from "../../components";
import "./index.css";

export function HomeScreen() {
  const { getProfile, perfil } = useProfile();
  const { getAllPosts, posts } = useTodosPosts();
  const [user, setUser] = useGlobalUser();
  const { postLogout } = useLogout();
  const { postComentar } = useComentar();
  const [comentario, setComentario] = useState({ comentario: "" });

  useEffect(() => {
    setTimeout(() => {
      if (perfil?.idUsuario && perfil?.idUsuario == undefined) {
        console.log(perfil?.idUsuario);
        localStorage.removeItem(USER_KEY);
      }
    }, 1200);
  });

  function handleChange(event) {
    const { name, value } = event.target;

    setComentario((oldComentario) => ({ ...oldComentario, [name]: value }));
  }

  function handleClickLogout() {
    postLogout();

    setUser(null);
    localStorage.removeItem(USER_KEY);
  }

  useEffect(() => {
    getProfile();
    getAllPosts();
  }, []);

  function handleComentar(idComentario) {

      postComentar(idComentario, comentario);

  }


  return (
    <>
      <div className="profile">
        <div className="profileCover">
          <Button onClick={handleClickLogout} className="logout">
            LOGOUT
          </Button>
          <Link to={"/perfil"}>
            <img
              className="imgProfile imgDirecao"
              src={perfil?.imgPerfil}
              alt="foto de perfil"
            />
          </Link>

          <h1 className="Logo">connect music</h1>
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
                      src={post.autorFoto}
                      alt="Foto do perfil"
                    />
                    <h1>
                      {post.autorApelido ? post.autorApelido : post.autorNome}
                    </h1>
                  </div>

                  <iframe
                    width="500"
                    height="318"
                    src={post.conteudo}
                    title="YouTube video player"
                  ></iframe>
                  <p>👍 {post.curtidas ? post.curtidas : 0}</p>
                  <Input
                    onChange={handleChange}
                    placeholder="Comentar"
                    type="text"
                  />
                </div>
              );
            })}
        </div>
      </div>
    </>
  );
}
