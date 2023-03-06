import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const useComentar = () => {
  const { handleRequest, data } = useRequest();

  function postComentar( idPost, comentario) {
    handleRequest(
      axiosInstance.post(`/comentario/${idPost}`, {
        comentario
      })
    );
  }
  return { postComentar };
};
