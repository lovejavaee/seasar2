/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * {@link JdbcManager}が管理しているトランザクション中の状態を持つインターフェースです。
 * トランザクション外で使われる場合は、SQLの実行後すぐに破棄されます。
 * 
 * @author higa
 * 
 */
public interface JdbcContext {

	/**
	 * 状態を破棄します。
	 */
	void destroy();

	/**
	 * トランザクション中に作成されたかどうかを返します。
	 * 
	 * @return トランザクション中に作成されたかどうか
	 */
	boolean isTransactional();

	/**
	 * キャッシュしているステートメントを返します。
	 * 
	 * @return キャッシュしているステートメント
	 */
	Statement getStatement();

	/**
	 * キャッシュしている準備されたステートメントを返します。
	 * 
	 * @param sql
	 *            SQL
	 * 
	 * @return キャッシュしている準備されたステートメント
	 */
	PreparedStatement getPreparedStatement(String sql);

	/**
	 * キャッシュしているカーソル用の準備されたステートメントを返します。
	 * 
	 * @param sql
	 *            SQL
	 * 
	 * @return キャッシュしている準備されたステートメント
	 */
	PreparedStatement getCursorPreparedStatement(String sql);

	/**
	 * キャッシュしている呼び出し可能なステートメントを返します。
	 * 
	 * @param sql
	 *            SQL
	 * 
	 * @return キャッシュしている呼び出し可能なステートメント
	 */
	CallableStatement getCallableStatement(String sql);
}
