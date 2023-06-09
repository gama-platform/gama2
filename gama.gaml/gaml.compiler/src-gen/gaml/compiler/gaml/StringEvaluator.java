/*******************************************************************************************************
 *
 * StringEvaluator.java, in gaml.compiler, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.compiler.gaml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Evaluator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gaml.compiler.gaml.StringEvaluator#getToto <em>Toto</em>}</li>
 *   <li>{@link gaml.compiler.gaml.StringEvaluator#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @see gaml.compiler.gaml.GamlPackage#getStringEvaluator()
 * @model
 * @generated
 */
public interface StringEvaluator extends Entry
{
  /**
   * Returns the value of the '<em><b>Toto</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Toto</em>' attribute.
   * @see #setToto(String)
   * @see gaml.compiler.gaml.GamlPackage#getStringEvaluator_Toto()
   * @model
   * @generated
   */
  String getToto();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.StringEvaluator#getToto <em>Toto</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Toto</em>' attribute.
   * @see #getToto()
   * @generated
   */
  void setToto(String value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(Expression)
   * @see gaml.compiler.gaml.GamlPackage#getStringEvaluator_Expr()
   * @model containment="true"
   * @generated
   */
  Expression getExpr();

  /**
   * Sets the value of the '{@link gaml.compiler.gaml.StringEvaluator#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Expression value);

} // StringEvaluator
