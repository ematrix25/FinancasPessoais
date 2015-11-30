CREATE OR REPLACE FUNCTION atualizarvalorfinaldeextrato()
  RETURNS trigger AS
$BODY$
	DECLARE
		qtd bigint;
		extrato "Extrato"%rowtype;
	BEGIN
		IF(TG_OP = 'INSERT') THEN
			SELECT * INTO extrato FROM "Extrato"
			WHERE "idExtrato" = NEW."idExtrato";
							
			IF(NEW.tipo = 'receita') THEN
				UPDATE "Extrato"
				SET "valorFinal" = extrato."valorFinal" + NEW.valor
				WHERE "idExtrato" = NEW."idExtrato";
			ELSIF(NEW.tipo = 'despesa') THEN
				UPDATE "Extrato"
				SET "valorFinal" = extrato."valorFinal" - NEW.valor
				WHERE "idExtrato" = NEW."idExtrato";
			END IF;
			
			RETURN NEW;
		ELSIF(TG_OP = 'UPDATE') THEN						
			IF(NEW."idExtrato" <> OLD."idExtrato") THEN
				SELECT * INTO extrato FROM "Extrato"
				WHERE "idExtrato" = OLD."idExtrato";
				
				IF(OLD.tipo = 'receita') THEN
					UPDATE "Extrato"
					SET "valorFinal" = extrato."valorFinal" - OLD.valor
					WHERE "idExtrato" = OLD."idExtrato";
				ELSIF(OLD.tipo = 'despesa') THEN
					UPDATE "Extrato"
					SET "valorFinal" = extrato."valorFinal" + OLD.valor
					WHERE "idExtrato" = OLD."idExtrato";
				END IF;	
				
				SELECT count(*) INTO qtd FROM "ItemDeExtrato"
				WHERE "idExtrato" = OLD."idExtrato";
					
				IF(qtd = 0) THEN
					DELETE FROM "Extrato"
					WHERE "idExtrato" = OLD."idExtrato";
				END IF;

				SELECT * INTO extrato FROM "Extrato"
				WHERE "idExtrato" = NEW."idExtrato";

				IF(NEW.tipo = 'receita') THEN
					UPDATE "Extrato"
					SET "valorFinal" = extrato."valorFinal" + NEW.valor
					WHERE "idExtrato" = NEW."idExtrato";
				ELSIF(NEW.tipo = 'despesa') THEN
					UPDATE "Extrato"
					SET "valorFinal" = extrato."valorFinal" - NEW.valor
					WHERE "idExtrato" = NEW."idExtrato";				
				END IF;
			ELSIF(NEW."idExtrato" = OLD."idExtrato") THEN			
				SELECT * INTO extrato FROM "Extrato"
				WHERE "idExtrato" = NEW."idExtrato";		
				
				IF(NEW.tipo = 'receita') THEN
					IF(OLD.tipo = 'receita') THEN
						UPDATE "Extrato"
						SET "valorFinal" = extrato."valorFinal" + (NEW.valor - OLD.valor)
						WHERE "idExtrato" = NEW."idExtrato";
					ELSIF(OLD.tipo = 'despesa') THEN
						UPDATE "Extrato"
						SET "valorFinal" = extrato."valorFinal" + (NEW.valor + OLD.valor)
						WHERE "idExtrato" = NEW."idExtrato";
					END IF;
				ELSIF(NEW.tipo = 'despesa') THEN
					IF(OLD.tipo = 'receita') THEN
						UPDATE "Extrato"
						SET "valorFinal" = extrato."valorFinal" - (NEW.valor + OLD.valor)
						WHERE "idExtrato" = NEW."idExtrato";
					ELSIF(OLD.tipo = 'despesa') THEN
						UPDATE "Extrato"
						SET "valorFinal" = extrato."valorFinal" - (NEW.valor - OLD.valor)
						WHERE "idExtrato" = NEW."idExtrato";
					END IF;
				END IF;
			END IF;	
						
			RETURN NEW;
		ELSIF(TG_OP = 'DELETE') THEN			
			SELECT * INTO extrato FROM "Extrato"
				WHERE "idExtrato" = OLD."idExtrato";
				
			IF(OLD.tipo = 'receita') THEN
				UPDATE "Extrato"
				SET "valorFinal" = extrato."valorFinal" - OLD.valor
				WHERE "idExtrato" = OLD."idExtrato";
			ELSIF(OLD.tipo = 'despesa') THEN
				UPDATE "Extrato"
				SET "valorFinal" = extrato."valorFinal" + OLD.valor
				WHERE "idExtrato" = OLD."idExtrato";
			END IF;
			
			SELECT count(*) INTO qtd FROM "ItemDeExtrato"
			WHERE "idExtrato" = OLD."idExtrato";
				
			IF(qtd = 0) THEN
				DELETE FROM "Extrato"
				WHERE "idExtrato" = OLD."idExtrato";
			END IF;
			
			RETURN OLD;
		END IF;
	END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER atualizarvalorfinaldeextrato
	AFTER INSERT OR UPDATE OR DELETE ON "ItemDeExtrato"
	FOR EACH ROW
	EXECUTE PROCEDURE atualizarvalorfinaldeextrato();