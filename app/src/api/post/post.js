import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const usePost = () => {
  const { handleRequest, data } = useRequest();

  function postCriarMeuPost({ conteudo, disponivel }) {
    handleRequest(
      axiosInstance.post("/posts/criarPost", {
        conteudo,
        disponivel,
      })
    );
  }
  return { postCriarMeuPost, post: data };
};
