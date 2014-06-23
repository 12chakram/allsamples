package com.myeclipseide.examples.myblog.jsf;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.myeclipseide.examples.myblog.persistence.EntityManagerHelper;
import com.myeclipseide.examples.myblog.persistence.Post;
import com.myeclipseide.examples.myblog.persistence.PostDAO;

/**
 * Maintains the state of the user interface and controls the the processing of
 * user CRUD actions on blog Post entities. A currentPost entity is maintained
 * for the actions that require a post context such as editCurrentPost and
 * deleteCurrentPost.
 */
public class PostController {

	/* application state constants */
	public static final String LISTS_STATE = "list";

	public static final String DETAILS_STATE = "details";

	public static final String CREATE_STATE = "create";

	public static final String EDIT_STATE = "edit";

	public static final String DELETE_STATE = "delete";

	/** The current post entity context for operations in the UI; may be null */
	private Post currentPost;

	/** JSF model of the list of ALL Post entities */
	private DataModel model;

	/** Maintains the lifecyle of Post entities */
	private PostDAO postDao;

	/** Creates a new instance of PostController */
	public PostController() {
		postDao = new PostDAO();
	}

	/**
	 * The current post entity context for operations in the UI
	 * 
	 * @return Post or null if the UI context is not focused on a specific Post
	 *         entity
	 */
	public Post getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(Post post) {
		this.currentPost = post;
	}

	/**
	 * Update and return the JSF UI dataModel of containing a list of all Post
	 * instances.
	 * 
	 * @return DataModel of all Post instances
	 */
	public DataModel getPostsDataModel() {
		model = new ListDataModel(postDao.findAll());
		return model;
	}

	/**
	 * Update the current post instance and display its details.
	 * 
	 * @return the result state: "details"
	 */
	public String showCurrentPostDetails() {
		setPostFromRequestParam();
		return DETAILS_STATE;
	}

	/**
	 * Prepare to create a new post instance.
	 * 
	 * @return the result state: "create"
	 */
	public String createPost() {
		this.currentPost = new Post();
		this.currentPost.setPosttime(new Date());
		return CREATE_STATE;
	}

	/**
	 * Persist the current post instance.
	 * 
	 * @return the result state: "list"
	 */
	public String saveCurrentPost() {
		try {
			EntityManagerHelper.beginTransaction();
			postDao.save(currentPost);
			EntityManagerHelper.commit();
			addSuccessMessage("Post was successfully created.");
		} catch (Exception ex) {
			try {
				addErrorMessage(ex.getLocalizedMessage());
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();
			} catch (Exception e) {
				addErrorMessage(e.getLocalizedMessage());
			}
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		return LISTS_STATE;
	}

	/**
	 * Update the current post instance and prepare to edit it.
	 * 
	 * @return the result state: "edit"
	 */
	public String editCurrentPost() {
		setPostFromRequestParam();
		return EDIT_STATE;
	}

	/**
	 * Persist the updated details of the current Post instance.
	 * 
	 * @return the result state: "list"
	 */
	public String updateCurrentPost() {
		try {
			EntityManagerHelper.beginTransaction();
			currentPost = postDao.update(currentPost);
			EntityManagerHelper.commit();
			addSuccessMessage("Post was successfully updated.");
		} catch (Exception ex) {
			try {
				addErrorMessage(ex.getLocalizedMessage());
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();
			} catch (Exception e) {
				addErrorMessage(e.getLocalizedMessage());
			}
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		return LISTS_STATE;
	}

	/**
	 * Permanently delete the current post instance
	 * 
	 * @return the result state: "list"
	 */
	public String deleteCurrentPost() {
		try {
			EntityManagerHelper.beginTransaction();
			Post post = getPostFromRequestParam();
			post = postDao.update(post);
			postDao.delete(post);
			EntityManagerHelper.commit();
			addSuccessMessage("Post was successfully deleted.");
		} catch (Exception ex) {
			try {
				addErrorMessage(ex.getLocalizedMessage());
				EntityManagerHelper.getEntityManager().getTransaction()
						.rollback();
			} catch (Exception e) {
				addErrorMessage(e.getLocalizedMessage());
			}
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		return LISTS_STATE;
	}

	/**
	 * Locate and return a post by its ID.
	 * 
	 * @param id
	 *            The id or PK of the post to locate
	 * @return Post
	 */
	public Post findPost(Integer id) {
		return postDao.findById(id);
	}

	/**
	 * Retrieve the post instance provided by the UI.
	 * 
	 * @return the Post
	 */
	public Post getPostFromRequestParam() {
		Post post = (Post) model.getRowData();
		return post;
	}

	/**
	 * Update the current post instance provided by the UI.
	 */
	public void setPostFromRequestParam() {
		Post post = getPostFromRequestParam();
		setCurrentPost(post);
	}

	public static void addErrorMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}

	public static void addSuccessMessage(String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("successInfo", facesMsg);
	}
}
