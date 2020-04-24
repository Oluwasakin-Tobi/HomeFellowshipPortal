<%@ include file="authheader.jsp" %>   

<div class="right_col" role="main">



                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="information">
                            <h3 style="margin-top: 0;font-weight: bold">!Oops</h3>  
                            <div class="form-container"> 
                                <div class="row">
                                    <div class="col-md-12"><h4 class="top-title">Access Denied</h4></div>
                                </div>   
                                <div id="errdiv" class="alert alert-danger">
                                    You are not authorised to access this page.
                                </div>    

                                <div class="modal fade ubn-popup-modal error" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header" style="color:#ff0000">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                <h4 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-exclamation-sign"></i> Error</h4>
                                            </div>
                                            <div class="modal-body" style="color:#ff0000">
                                                System Error - Contact Admin. 
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" data-dismiss="modal" class="btn btn-blue">Try Again</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>    
                            </div>
                            <div role="tabpanel" class="tab-pane" id="switch">&nbsp;</div>
                            <div role="tabpanel" class="tab-pane" id="security">&nbsp;</div>
                            <div role="tabpanel" class="tab-pane" id="register">&nbsp;</div>
                            <div role="tabpanel" class="tab-pane" id="faq">&nbsp;</div>
                        </div>	   

                    </div>
                </div> 