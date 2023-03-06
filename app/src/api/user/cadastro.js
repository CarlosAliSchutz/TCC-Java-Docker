import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const useCadastro = () => {
  const { handleRequest, data } = useRequest();

  function postCadastro({
    nome,
    email,
    apelido,
    dataNascimento,
    senha,
    imgPerfil,
    permissoes,
  }) {
    handleRequest(
      axiosInstance.post("/usuarios", {
        nome,
        email,
        apelido,
        dataNascimento,
        senha,
        imgPerfil,
        permissoes,
      })
    );
  }
  return { postCadastro, usuario: data };
};
