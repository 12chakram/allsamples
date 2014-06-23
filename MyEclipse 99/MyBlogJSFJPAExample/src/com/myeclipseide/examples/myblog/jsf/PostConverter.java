package com.myeclipseide.examples.myblog.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.myeclipseide.examples.myblog.persistence.Post;

public class PostConverter implements Converter {

	public Object getAsObject(FacesContext facesContext,
			UIComponent uIComponent, String string) {
		if (string == null) {
			return null;
		}
		Integer id = new Integer(string);
		PostController controller = (PostController) facesContext
				.getApplication().getELResolver().getValue(
						facesContext.getELContext(), null, "postController");

		return controller.findPost(id);
	}

	public String getAsString(FacesContext facesContext,
			UIComponent uIComponent, Object object) {
		String result = null;

		if (object != null) {
			if (object instanceof Post) {
				Post o = (Post) object;
				result = "" + o.getId();
			}
		} else {
			throw new IllegalArgumentException("object:" + object + " of type:"
					+ object.getClass().getName()
					+ "; expected type: com.myeclipseide.myblog.Post");
		}

		return result;
	}

}
